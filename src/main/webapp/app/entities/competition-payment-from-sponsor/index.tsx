import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CompetitionPaymentFromSponsor from './competition-payment-from-sponsor';
import CompetitionPaymentFromSponsorDetail from './competition-payment-from-sponsor-detail';
import CompetitionPaymentFromSponsorUpdate from './competition-payment-from-sponsor-update';
import CompetitionPaymentFromSponsorDeleteDialog from './competition-payment-from-sponsor-delete-dialog';

const CompetitionPaymentFromSponsorRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CompetitionPaymentFromSponsor />} />
    <Route path="new" element={<CompetitionPaymentFromSponsorUpdate />} />
    <Route path=":id">
      <Route index element={<CompetitionPaymentFromSponsorDetail />} />
      <Route path="edit" element={<CompetitionPaymentFromSponsorUpdate />} />
      <Route path="delete" element={<CompetitionPaymentFromSponsorDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CompetitionPaymentFromSponsorRoutes;
