import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './ai-tool.reducer';

export const AiToolUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const aiToolEntity = useAppSelector(state => state.aiTool.entity);
  const loading = useAppSelector(state => state.aiTool.loading);
  const updating = useAppSelector(state => state.aiTool.updating);
  const updateSuccess = useAppSelector(state => state.aiTool.updateSuccess);

  const handleClose = () => {
    navigate('/ai-tool');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }

    const entity = {
      ...aiToolEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...aiToolEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.aiTool.home.createOrEditLabel" data-cy="AiToolCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.aiTool.home.createOrEditLabel">Create or edit a AiTool</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="ai-tool-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiTool.name')}
                id="ai-tool-name"
                name="name"
                data-cy="name"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiTool.description')}
                id="ai-tool-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiTool.vendor')}
                id="ai-tool-vendor"
                name="vendor"
                data-cy="vendor"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiTool.linkUrl')}
                id="ai-tool-linkUrl"
                name="linkUrl"
                data-cy="linkUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiTool.isActive')}
                id="ai-tool-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiTool.createdBy')}
                id="ai-tool-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiTool.createdOn')}
                id="ai-tool-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiTool.updatedBy')}
                id="ai-tool-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiTool.updatedOn')}
                id="ai-tool-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/ai-tool" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default AiToolUpdate;
