import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getVideoUsers } from 'app/entities/video-user/video-user.reducer';
import { getEntities as getAiTools } from 'app/entities/ai-tool/ai-tool.reducer';
import { createEntity, getEntity, reset, updateEntity } from './ai-tool-session.reducer';

export const AiToolSessionUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const videoUsers = useAppSelector(state => state.videoUser.entities);
  const aiTools = useAppSelector(state => state.aiTool.entities);
  const aiToolSessionEntity = useAppSelector(state => state.aiToolSession.entity);
  const loading = useAppSelector(state => state.aiToolSession.loading);
  const updating = useAppSelector(state => state.aiToolSession.updating);
  const updateSuccess = useAppSelector(state => state.aiToolSession.updateSuccess);

  const handleClose = () => {
    navigate('/ai-tool-session');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getVideoUsers({}));
    dispatch(getAiTools({}));
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
      ...aiToolSessionEntity,
      ...values,
      user: videoUsers.find(it => it.id.toString() === values.user?.toString()),
      tool: aiTools.find(it => it.id.toString() === values.tool?.toString()),
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
          ...aiToolSessionEntity,
          user: aiToolSessionEntity?.user?.id,
          tool: aiToolSessionEntity?.tool?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.aiToolSession.home.createOrEditLabel" data-cy="AiToolSessionCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.aiToolSession.home.createOrEditLabel">Create or edit a AiToolSession</Translate>
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
                  id="ai-tool-session-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolSession.isPaymentLinkGenerated')}
                id="ai-tool-session-isPaymentLinkGenerated"
                name="isPaymentLinkGenerated"
                data-cy="isPaymentLinkGenerated"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolSession.isPaid')}
                id="ai-tool-session-isPaid"
                name="isPaid"
                data-cy="isPaid"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolSession.isVideoGenerated')}
                id="ai-tool-session-isVideoGenerated"
                name="isVideoGenerated"
                data-cy="isVideoGenerated"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolSession.isVideoDownloaded')}
                id="ai-tool-session-isVideoDownloaded"
                name="isVideoDownloaded"
                data-cy="isVideoDownloaded"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolSession.isActive')}
                id="ai-tool-session-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolSession.createdBy')}
                id="ai-tool-session-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolSession.createdOn')}
                id="ai-tool-session-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolSession.updatedBy')}
                id="ai-tool-session-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolSession.updatedOn')}
                id="ai-tool-session-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                id="ai-tool-session-user"
                name="user"
                data-cy="user"
                label={translate('affliateMarketplaceApp.aiToolSession.user')}
                type="select"
              >
                <option value="" key="0" />
                {videoUsers
                  ? videoUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="ai-tool-session-tool"
                name="tool"
                data-cy="tool"
                label={translate('affliateMarketplaceApp.aiToolSession.tool')}
                type="select"
              >
                <option value="" key="0" />
                {aiTools
                  ? aiTools.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/ai-tool-session" replace color="info">
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

export default AiToolSessionUpdate;
