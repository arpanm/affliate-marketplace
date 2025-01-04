import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Sponsor from './sponsor';
import SponsorDetail from './sponsor-detail';
import SponsorUpdate from './sponsor-update';
import SponsorDeleteDialog from './sponsor-delete-dialog';

const SponsorRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Sponsor />} />
    <Route path="new" element={<SponsorUpdate />} />
    <Route path=":id">
      <Route index element={<SponsorDetail />} />
      <Route path="edit" element={<SponsorUpdate />} />
      <Route path="delete" element={<SponsorDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default SponsorRoutes;
